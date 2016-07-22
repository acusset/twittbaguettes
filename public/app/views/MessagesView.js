window.MessagesView = Backbone.View.extend({

    model: MessagesCollection,

    el: "div#container",

    render: function (model) {

        this.$el.html(""); // lets render this view

        for (var i = 0; i < this.model.length; ++i) {
            var messageView = new MessageView({model: this.model.at(i)});

            this.$el.append(messageView.$el);
            messageView.render();
        }
        return this;
    },

    // activeScrollSpy: function () {
    //
    //     var options = [
    //         {selector: ".view", offset: 400, callback: loadMoreMessages}
    //     ];
    //     Materialize.scrollFire(options);
    // }

});