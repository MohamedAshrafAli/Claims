(function() {
    'use strict';

    function UserAssignmentController($scope, $element, $attrs, $filter, AutocompleteService, companyId) {

        this.storeUser = [];
        console.log(this.users);

        this.$onInit = function() {
            this.storeUser = angular.copy(this.users);
        }

        this.filterByUser = function(selectedUser) {
            if (selectedUser == "" || selectedUser == undefined || selectedUser == null) {
                this.users = angular.copy(this.storeUser);
                if (this.selectedUser) {
                    this.selectedUser = {};
                }
            } else {
                AutocompleteService.getStatusCountByUser({userId: selectedUser.UserId}, (resp) => {
                    selectedUser.assigned = parseInt(resp.rowData[0].Total);
                    this.users = [selectedUser];
                    //this.users = $filter('filter')(this.storeUser, { name: selectedUser });
                }, onerror);
                function onerror() {
                    console.log("Error Occured");
                }

            }
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
                    users: '=',
                    selectedUser: '=',
                    assignedToSelectedUser: '&'
                },
                templateUrl: "resources/component/userAssignment-component/userAssignment.component.html",
                controller: UserAssignmentController
            });
    
})();