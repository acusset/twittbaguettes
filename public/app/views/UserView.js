var UserView = Backbone.View.extend({

    el: "div#container",

    tagName: "div",
    className: "user",

    template: _.template($('#user-template').html()),
    model: User,

    render: function() {
        this.$el.html(this.template(this.model.attributes));
        return this;
    }
});