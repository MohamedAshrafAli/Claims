(function() {
    'use strict';

    function UserAssignmentController($scope, $element, $attrs, $filter, AutocompleteService, companyId) {

        this.storeUser = [];

        this.$onInit = function() {
            this.storeUser = angular.copy(this.users);
        }

        this.filterByUser = function(userSearchText) {
            if (userSearchText == "" || userSearchText == undefined || userSearchText == null) {
                this.users = angular.copy(this.storeUser);
                if (this.selectedUser) {
                    this.selectedUser = {};
                }
            } else {
                this.users = $filter('filter')(this.storeUser, { name: userSearchText });
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
                    users: '<',
                    selectedUser: '=',
                    assignedToSelectedUser: '&'
                },
                templateUrl: "resources/component/userAssignment-component/userAssignment.component.html",
                controller: UserAssignmentController
            });
    
})();