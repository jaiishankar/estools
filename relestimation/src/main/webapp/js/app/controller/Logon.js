Ext.define('estools.controller.Logon', {
    extend: 'Ext.app.Controller',
    views: [
        'logon.LogonWindow',
        'applicationlayout.Header',
        'Viewport',
        'applicationlayout.MainScreen'
    ],
    refs:[{
            ref:'mainscreen',
            selector: 'applicationmainscreen'
    }],
    init: function() {
        
        this.control({
            'button[action=showUserTab]': {
                click: this.loadUsersTab
            }
        });
        this.control({
            'button[action=signup]': {
                click: this.signup
            }
        });
        this.control({
            'button[action=logon]': {
                click: this.login
            }
        });
        this.control({
            'button[action=displaySignup]': {
                click: this.displaySignup
            }
        });
        this.control({
            'button[action=cancelSignup]': {
                click: this.cancelSignup
            }
        });

        this.control({
            'button[action=logout]': {
                click: this.logout
            }
        });

        this.control({
            'button[action=saveProfile]': {
                click: this.saveProfile
            }
        });

        this.control({
            'button[action=cancelProfile]': {
                click: this.cancelProfile
            }
        });
    },
    loadUsersTab: function(button){
//        console.log(button);
        var mainScreen = this.getMainscreen(); 
        mainScreen.addGroupsTab();
        
    },
    signup: function(button) {
        var win = button.up('window'),
                formPanel = button.up('form'),
                formValues = formPanel.getForm().getValues();

        //We don't need the password repeated here
        delete formValues.repeatpassword;

        Ext.Ajax.request({
            method: 'POST',
            url: './v1/users/signup',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: formValues,
            scope: this,
            success: function(response, options) {

                var responseData = Ext.decode(response.responseText);

                if (responseData.success) {
                    console.log(responseData.results.id);
                    var userId = responseData.results.id;
                    this.displayProfileDetails(userId, win,responseData.results );
                }
                else {
                    var errorObject = responseData.results;
                    Ext.Msg.show({
                        title: errorObject.title,
                        msg: errorObject.msg,
                        icon: Ext.Msg.ERROR,
                        buttons: Ext.Msg.OK
                    });
                    form.markInvalid(errors);
                }
            }
        });
    },
    displayProfileDetails: function(userId, win, data) {
        var cardLayout = win.getLayout(),
                profilesignupFormPanel = cardLayout.getNext();
        profilesignupFormPanel.getForm().reset();
        profilesignupFormPanel.getForm().findField("id").setValue(userId);
        profilesignupFormPanel.getForm().findField("uname").setValue(data.uname);
        profilesignupFormPanel.getForm().findField("passcode").setValue(data.passcode);
        
        win.getLayout().setActiveItem(2);
    },
    login: function(button) {
        var win = button.up('window'),
                formPanel = button.up('form');

        Ext.Ajax.request({
            method: 'POST',
            url: './v1/users/logon',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: formPanel.getForm().getValues(),
            scope: this,
            success: function(response, options) {

                var responseData = Ext.decode(response.responseText);

                if (responseData.success) {
                    win.close();
                    //Set the logged in user
                    globalvar.currentUserId = responseData.results.id;
                    estools.utils.updateCache(globalvar);
                    Ext.create('estools.view.Viewport');
                }
                else {
                    var errorObject = responseData.results;
                    Ext.Msg.show({
                        title: errorObject.title,
                        msg: errorObject.msg,
                        icon: Ext.Msg.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        });
    },
    displaySignup: function(button) {
        var win = button.up('window'),
                cardLayout = win.getLayout(),
                signupFormPanel = cardLayout.getNext();

        win.setTitle("Signup");
        //Clear the form when switching cards
        signupFormPanel.getForm().reset();

        //Switch to the logon form
        win.getLayout().setActiveItem(1);

        //Set the focus to the name textfield
        signupFormPanel.getNameTextField().focus(null, null);
    },
    cancelProfile: function(button) {
        button.up('window').setTitle("Logon");
        this.displayLogon(button);
    },
    cancelSignup: function(button) {
        button.up('window').setTitle("Logon");
        this.displayLogon(button);
    },
    saveProfile: function(button) {

        var win = button.up('window'),
                formPanel = button.up('form');
        console.log(formPanel.getForm().getValues());
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/users/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: formPanel.getForm().getValues(),
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    Ext.Msg.alert("Signup Success", "Please Login to access the application");
                    button.up('window').setTitle("Logon");
                    this.displayLogon(button);
                }
                else {
                    var errorObject = responseData.results;
                    Ext.Msg.show({
                        title: errorObject.title,
                        msg: errorObject.msg,
                        icon: Ext.Msg.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }});

    },
    displayLogon: function(button) {
        var win = button.up('window'),
                cardLayout = win.getLayout(),
                logonFormPanel = cardLayout.getPrev();

        //Clear the form when switching cards
        logonFormPanel.getForm().reset();

        //Switch to the signup form 
        win.getLayout().setActiveItem(0);

        //Set the focus to the name textfield
        logonFormPanel.getNameTextField().focus(null, null);
    },
    getLoggedUserId: function() {
        return parseInt(localStorage.getItem("loggedUser"));
    },
    logout: function(button) {
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/users/logout',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: {
                id: this.getLoggedUserId()
            },
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    //localStorage.setItem("loggedUser", 0);
                    globalvar.currentUserId = 0;
                    estools.utils.updateCache(globalvar);
                    var viewport = button.up('viewport');
                    viewport.destroy();
                    Ext.create('estools.view.logon.LogonWindow').show();
                }
                else {
                    /**
                     * This is added to make sure the user is logged out even if
                     * there is any issues in the backend. 
                     */
                    //localStorage.setItem("loggedUser", 0);
                    globalvar.currentUserId = 0;
                    estools.utils.updateCache(globalvar);
                    var viewport = button.up('viewport');
                    viewport.destroy();
                    Ext.create('estools.view.logon.LogonWindow').show();
                }
            }
        });
    }
});