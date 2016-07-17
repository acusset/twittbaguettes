var EditMessageFormView = Backbone.View.extend({

    el: "div#message-editor",

    template: _.template($('#edit-message-form-template').html()),
    model: Message,

    render: function() {
        this.$el.html(this.template(this.model.attributes));
        return this;
    }
});