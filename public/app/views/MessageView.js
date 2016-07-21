window.MessageView = Backbone.View.extend({

    tagName: "div",
    className: "message",
    id: function () {
        return this.model.get("id")
    },

    template: _.template($('#message-template').html()),

    model: Message,

    render: function () {
        this.$el.html(this.template(this.model.attributes));
        return this;
    },

    events: {
        "click .delete": "deleteMessage",
        "click .edit": "editMessage"
    },

    deleteMessage: function () {
        this.$el.attr("class", this.className + " animated fadeOutLeft");
        var context = this;
        setTimeout(function () {
            context.$el.html("");
            context.model.destroy();
            Materialize.toast("Message supprimé", 3000);
        }, 1300);
    },

    editMessage: function () {
        var url = "message/" + this.model.get("id") + "/edit";
        window.router.navigate(url, {trigger: true});
    }
});