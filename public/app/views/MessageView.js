window.MessageView = Backbone.View.extend({

    tagName: "div",
    className: "message",

    template: _.template($('#message-template').html()),
        model: Message,
    
    render: function() {
        this.$el.html(this.template(this.model.attributes));
        return this;
    }

});