appRouter = Backbone.Router.extend({

    routes: {
        "me": "profil",
        "messages(/:page)(/:perPage)": "getMessages",
        "new-message": "addMessage",
        "login": "login",
        "*action": "defaultRoute"
    },

    defaultRoute: function (action) {


    },

    profil: function () {
        var currentUser = new User({id: 1});
        currentUser.fetch({
            success: function (data) {
                var userView = new UserView({model: currentUser});
                userView.render();
            }
        });

    },

    getMessages: function (page, perPage) {
        
        // Accueil
        var messgesCollection = new MessagesCollection({page: page, perPage: perPage});
        messgesCollection.fetch({
            success: function () {
                var messagesView = new MessagesView({model: messgesCollection});
                messagesView.render();
                Materialize.updateTextFields();
            }
        });
        
    },

    addMessage: function () {
        new NewMessageFormView().render();
    },

    login: function () {
        new LoginFormView().render();
    }

});