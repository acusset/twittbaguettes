/**
 * Created by Alexandre on 23/06/2016.
 * Lanceur de l'application
 */

$(document).ready(function () {

    moment.locale("fr");
    $(".button-collapse").sideNav();
    window.router = new appRouter;
    Backbone.history.start();
});