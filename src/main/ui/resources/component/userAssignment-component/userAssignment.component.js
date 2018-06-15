(function() {
    'use strict';

    function UserAssignmentController($scope, $element, $attrs, $filter, AutocompleteService, companyId, $q) {

        this.storeUser = [];
        this.$onInit = function() {
            var params = {compId : companyId, userName : "%"};
            AutocompleteService.getUserList(params, (resp) => {
                this.users = resp.rowData;
                var promises = [];
                var updatedUsers = [];
                this.users.forEach((item) => {
                    promises.push(AutocompleteService.getStatusCountByUser({userId: item.UserId}).$promise);                    
                })
                $q.all(promises).then((resp) => {
                    this.users.forEach((user, index) => {
                        constructAssignedAndPending(user, resp[index]);
                        updatedUsers.push(user);
                    })
                    this.users = updatedUsers;
                    this.storeUser = angular.copy(this.users);
                }).catch((err) => {
                    alert("Error");
                })
            })
        }

        function constructAssignedAndPending(user, resp) {
            var data = resp.rowData;
            var count = {"wipCount" : 0, "approvedCount" : 0};
            data.forEach((res) => {
                if(res.Status == 'WIP')
                    count.wipCount = parseInt(res.Total);
                if(res.Status == 'APP')
                    count.approvedCount = parseInt(res.Total);
            });
            user.assigned = count.wipCount;
            user.pending = Math.abs(count.wipCount - count.approvedCount);
        }

        this.filterByUser = function(selectedUser) {
            if (selectedUser == "" || selectedUser == undefined || selectedUser == null) {
                this.users = angular.copy(this.storeUser);
                if (this.selectedUser) {
                    this.selectedUser = {};
                }
            } else {
                AutocompleteService.getStatusCountByUser({userId: selectedUser.UserId}, (resp) => {
                    selectedUser.assigned =  resp.rowData.length ? parseInt(resp.rowData[0].Total) :  0;
                    this.users = [selectedUser];
                }, onerror);
            }
        }
        
        function onerror() {
            console.log("Error Occured");
        }

        this.getAutoCompleteList = function(userSearchText) {
            if(userSearchText.length < 3) {
                return [];
            }
            var params = {
                compId : companyId,
                userName : userSearchText + "%"
            }
            return AutocompleteService.getUserList(params).$promise.then(function(resp) {
                return resp.rowData;
            });
        }
    }

    angular
        .module('claims')
            .component('userAssignmentComponent', {
                bindings : {
                    headers : '<',
                    selectedUser: '=',
                    assignedToSelectedUser: '&'
                },
                templateUrl: "resources/component/userAssignment-component/userAssignment.component.html",
                controller: UserAssignmentController
            });
    
})();