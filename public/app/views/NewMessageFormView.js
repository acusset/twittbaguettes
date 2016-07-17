window.NewMessageFormView = Backbone.View.extend({

    el: "div#container",

    template: _.template($('#new-message-form-template').html()),

    render: function () {
        this.$el.html(this.template());
        return this;
    },

    events: {
        "submit": "essai",
        "click button.envoyer": "submit"
    },

    submit: function () {
        var values = this.getFormInputs();
        var newMessage = new Message(values);
        console.log(newMessage.save());
        console.log("ok");
    },

    getFormInputs: function () {
        return {
            content: $('textarea').val(),
            url: $('#icon_link').val(),
            img: $('#icon_img').val()
        }
    }

});