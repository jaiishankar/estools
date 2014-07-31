Ext.define('estools.view.logon.LogonFormPanel', {
    extend: 'Ext.form.Panel',
    alias: 'widget.logonformpanel',
    windowTitle: 'log',
    url: './v1/users/logon',
    bodyPadding: 5,
    layout: 'border',
    border:false,
    bodyStyle: 'background:white;',
    initComponent: function() {
        // set the login form into the window
        this.items = [
            this.createCenterPanel(),
            this.createEastPanel()
        ];
        this.buttons = [
            this.createDisplaySignupButton(),
            this.createLoginButton()
        ];

        // call the superclass's initComponent implementation
        this.callParent();
    },
    createNameTextfield: function() {
        this.nameTextField = Ext.create('Ext.form.field.Text', {
            name: 'uname',
            fieldLabel: 'User Name',
            allowBlank: false
        });

        return this.nameTextField;
    },
    createPasswordTextfield: function() {
        return {
            xtype: 'textfield',
            name: 'passcode',
            inputType: 'password',
            fieldLabel: 'Password',
            allowBlank: false
        };
    },
    createLoginButton: function() {
        return {
            xtype: 'button',
            action: 'logon',
            formBind: true,
            text: 'Login'
        };
    },
    createDisplaySignupButton: function() {
        return {
            xtype: 'button',
            action: 'displaySignup',
            text: 'Signup'
        };
    },
    getNameTextField: function() {
        return this.nameTextField;
    },
    createLoginImage: function() {
        return {
            border:false,
            html: '<img src="./images/login_image.jpeg" width="100" height="100" />'
        };

    },
    createCenterPanel: function() {
        return {
            xtype: 'panel',
            region: 'center',
            bodyStyle: 'background:white;',
            padding: 10,
            border:false,
            items: [
                this.createNameTextfield(),
                this.createPasswordTextfield()
            ]
        };
    },
    createEastPanel: function() {
        return {
            xtype: 'panel',
            region: 'east',
            border:false,
            items: [
                this.createLoginImage()
            ]
        };
    }
});