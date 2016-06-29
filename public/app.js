/**
 * Created by Alexandre on 23/06/2016.
 * Lanceur de l'application
 */

$(document).ready(function () {

    var messgesCollection = new MessagesCollection();
    messgesCollection.fetch({
        success: function () {
            var messagesView = new MessagesView({model: messgesCollection});
            messagesView.render();
        },
    });

});