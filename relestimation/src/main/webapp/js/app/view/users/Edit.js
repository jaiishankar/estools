Ext.define('estools.view.users.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.useredit',
    // Default proeprties
    layout: 'fit',
    autoShow: false,
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Edit User Window';

        // Create and add the form
        this.items = [
            {
                xtype: 'form',
                itemId: 'editForm',
                border: 0,
                bodyPadding: 5,
                items: [
                    {
                        xtype: 'hiddenfield',
                        name: 'id',
                        value: 0
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'usernameField',
                        name: 'uname',
                        fieldLabel: 'User Name'
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'firstnameField',
                        name: 'fname',
                        fieldLabel: 'First Name'
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'lastnameField',
                        name: 'lname',
                        fieldLabel: 'Last Name'
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'midlenameField',
                        name: 'mname',
                        fieldLabel: 'Middle Name'
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'emailField',
                        name: 'email',
                        fieldLabel: 'Email'
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'phoneField',
                        name: 'phone',
                        fieldLabel: 'Phone'
                    }
                ]
            }
        ];

        // Add the save/cancel buttons
        this.buttons = [
            {
                itemId: 'saveButton',
                text: 'Upadte', //this.getSaveButtonText(),
                action: 'save'
            },
            {
                itemId: 'cancelButton',
                text: 'Cancel',//this.getCancelButtonText(),
                scope: this,
                handler: this.destroy
            }
        ];

        this.callParent();
    }
});