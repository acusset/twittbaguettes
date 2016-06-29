/**
 * Created by Antoine on 20/06/2016.
 */

var MessageView = Backbone.View.extend({

    tagName: "div",
    className: "message_view",

    template: _.template($('#message-template').html()),
        model: Message,
    
    render: function( model ) {
        this.$el.html(this.template(this.model.attributes));
        return this;
    },

});