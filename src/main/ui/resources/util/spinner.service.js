(function() {
    'use strict';

    angular
        .module('claims')
        .factory('SpinnerService', SpinnerService);

    function SpinnerService() {
        var docheight = $(document).height();
        var blockerTemplate = '<div id="overlay" style="opacity :0.75;position: absolute; top: 0; left: 0; height:'+docheight+'px; width: 100%; background-color: #eeeeee; z-index: 5000">'+
                                    '<div class="container">'+
                                       '<div class="middle-loading-box">'+
                                           '<div class="center-block">'+
                                              '<span class="fa fa-spinner fa-spin fa-3x buffer"></span>'+
                                           '</div>'+
                                       '</div>'+
                                   '</div>'+
                               '</div>';
        return {
            blockInProgress: false,
            start: function() {
                this.blockInProgress = true;
                $("body").append($(blockerTemplate));
            },

            stop: function() {
                this.blockInProgress = false;
                $("#overlay").remove();
            }
        }
    }
})();
