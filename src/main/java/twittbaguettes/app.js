/**
 * Created by Alexandre on 23/06/2016.
 * Lanceur de l'application
 */

$(document).ready(function () {
    
    moment.locale("fr");
    new appRouter;
    Backbone.history.start();


    // new NewMessageFormView().render();
    // var message = new Message({id: 2});
    // message.fetch({
    //     success: function (data) {
    //        var tmp = new EditMessageFormView({model: message});
    //         tmp.render();
    //         Materialize.updateTextFields();
    //     }
    // });


});