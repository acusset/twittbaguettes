window.appRouter = Backbone.Router.extend({

    initialize: function () {
        this.on("route", function (route, params) {
            var currentUser = new User();
            currentUser.fetch({
                success: function () {
                    window.currentUser = currentUser;
                    var attributes = currentUser.attributes;
                    for (var property in attributes) {
                        if (property == "authorities") {
                            localStorage.setItem("authority", currentUser.get(property)[0].authority);
                        }
                        localStorage.setItem(property, currentUser.get(property));
                    }
                }
            });
        })
    },

    routes: {
        "me": "profil",
        "messages(/:page)(/:perPage)": "getMessages",
        "message/:id/edit": "editMessage",
        "new-message": "addMessage",
        "utilisateurs": "getUsers",
        "u/:id": "publicProfile",
        "u/:id/edit": "editUser",
        "*action": "defaultRoute"
    },

    publicProfile: function (id) {
        var myUser = new User({id: id});
        myUser.fetch({
            success: function () {
                var userView = new UserProfileView({model: myUser});
                userView.render();
            },
            error: function () {
                Materialize.toast("Cet utilisateur n'existe pas", 5000);
                window.router.navigate("messages", {trigger: true});
            }
        });
    },

    defaultRoute: function () {
        return this.navigate("messages", {trigger: true});
    },

    profil: function () {
        var user = window.currentUser;
        console.log(user);
        user.fetch({
            success: function () {
                var userView = new UserProfileView({model: user});
                userView.render();
            },
            error: function() {
                Materialize.toast("Erreur lors du chargement",3000);
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

    editMessage: function (id) {
        message = new Message({id: id});
        message.fetch({
            success: function () {
                if (message.get("user").id == localStorage.getItem("id")) {
                    var messageView = new EditMessageFormView({model: message});
                    messageView.render();
                } else {
                    Materialize.toast("Vous n'avez le droit de modifier ce message", 5000);
                    window.router.navigate("messages", {trigger: true});
                }
            },
            error: function () {
                Materialize.toast("Message non trouvé", 5000);
                window.history.back();
            }
        });
    },

    editUser: function (id) {
        var user = new User({id: id});
        user.fetch({
            success: function () {
                if (user.id == localStorage.getItem("id")) {
                    var userView = EditUserFormView({model: user});
                    userView.render();
                } else {
                    Materialize.toast("Vous n'avez le droit de modifier ce user", 5000);
                    window.router.navigate("messages", {trigger: true});
                }
            },
            error: function () {
                Materialize.toast("Message non trouvé", 5000);
                window.history.back();
            }
        });
    },

    getUsers: function (page, perPage) {
        // Accueil
        var usersCollection = new UsersCollection({page: page, perPage: perPage});
        usersCollection.fetch({
            success: function () {
                var usersView = new UsersView({model: usersCollection});
                usersView.render();
                Materialize.updateTextFields();
            }
        });
    }
});