Ext.define('estools.view.logon.ProfileSignupFormPanel', {
    extend: 'Ext.form.Panel',
    alias: 'widget.profilesignupformpanel',
    url: './v1/users/',
    bodyPadding: 5,
    windowTitle: 'Profile',
    currentUserId: 0,
    border: false,
    autoScroll: true,
    initComponent: function() {
        this.items = [
            this.createFNameTextfield(),
            this.createMNameTextfield(),
            this.createLNameTextfield(),
            {
                xtype: 'hiddenfield',
                name: 'id',
                value: 0
            },
            {
                xtype: 'hiddenfield',
                name: 'uname',
                value: ""
            },
            {
                xtype: 'hiddenfield',
                name: 'passcode',
                value: ""
            },
            {
                xtype: 'hiddenfield',
                name: 'isLoggedIn',
                value: false
            },
            {
                xtype: 'hiddenfield',
                name: 'isActive',
                value: true
            },
            {
                xtype: 'textfield',
                name: 'email',
                vtype: 'email',
                fieldLabel: 'Email'
            },
            {
                xtype: 'numberfield',
                name: 'phone',
                fieldLabel: 'Phone',
                vtype: 'phone',
                hideTrigger: true

            }
        ],
                this.buttons = [
                    this.createCancelSignupButton(),
                    this.createSignupButton()
                ];

        this.callParent();
    },
    createFNameTextfield: function() {
        this.nameTextField = Ext.create('Ext.form.field.Text', {
            name: 'fname',
            fieldLabel: 'First Name',
            allowBlank: true
        });
        return this.nameTextField;
    },
    createLNameTextfield: function() {
        this.nameTextField = Ext.create('Ext.form.field.Text', {
            name: 'lname',
            fieldLabel: 'Last Name',
            allowBlank: true
        });

        return this.nameTextField;
    },
    createMNameTextfield: function() {
        this.nameTextField = Ext.create('Ext.form.field.Text', {
            name: 'mname',
            fieldLabel: 'Middle Name',
            allowBlank: true
        });

        return this.nameTextField;
    },
    createCancelSignupButton: function() {
        return {
            xtype: 'button',
            action: 'cancelProfile',
            text: 'Cancel'
        };
    },
    createSignupButton: function() {
        return {
            xtype: 'button',
            action: 'saveProfile',
            formBind: true,
            text: 'Save'
        };
    }
});

