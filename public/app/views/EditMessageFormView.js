window.EditMessageFormView = Backbone.View.extend({

    el: "div#container",

    id: function () {
        return this.model.get("id")
    },

    template: _.template($('#edit-message-form-template').html()),
    model: Message,

    render: function() {
        this.$el.html("");
        this.$el.html(this.template(this.model.attributes));
        return this;
    },

    events: {
        "click .post": "postMessage",
        "click .return": "previousPage"
    },

    postMessage: function () {
        var values = this.getFormInputs();
        var newMessage = new Message(values);
        if (!this.isFormEmpty()) {
            var context = this;
            newMessage.save({},{
                success: function() {
                    Materialize.toast("Message édité",3000);
                    context.cleanup();
                    router.navigate("messages", {trigger: true});
                },
                error: function() {
                    Materialize.toast("Une erreure est survenue",3000);
                }
            });
        } else {
            Materialize.toast("Ce message est vide !",3000);
        }
    },

    isFormEmpty: function () {
        return _.isEmpty($('textarea').val());
    },

    getFormInputs: function () {
        return {
            id: this.id(),
            content: $('textarea').val(),
            url: $('#icon_link').val(),
            img: $('#icon_img').val()
        }
    },

    previousPage: function () {
        this.cleanup();
        window.router.navigate("/", {trigger: true});
    },

    cleanup: function() {
        this.undelegateEvents();
        this.$el.html();
    }
});