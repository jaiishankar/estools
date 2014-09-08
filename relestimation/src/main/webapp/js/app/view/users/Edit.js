Ext.define('estools.view.users.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.useredit',
    // Default proeprties
    layout: 'fit', width: '680', height: '450',
    autoShow: false,
    maximized: true,
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Add/Edit User Window';
        this.groupsStore = Ext.StoreMgr.get("DevGroup");
        if (this.groupsStore) {
            if (this.groupsStore.getCount() <= 0) {
                this.groupsStore.load();
            } else {
                this.groupsStore.sync();
            }
        }
        // Create and add the form
        this.items = [{
                xtype: 'tabpanel',
                items: [{
                        title: 'Main',
                        xtype: 'form',
                        itemId: 'editForm',
                        border: 0,
                        bodyPadding: 5,
                        buttons: [
                            {
                                itemId: 'saveButton',
                                text: 'Update', //this.getSaveButtonText(),
                                action: 'save'
                            }
                        ],
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
                                fieldLabel: 'User Name',
                                allowBlank: false
                            },
                            {
                                xtype: 'textfield',
                                itemId: 'passwordField',
                                name: 'passcode',
                                fieldLabel: 'Password',
                                allowBlank: false
                            },
                            {
                                xtype: 'textfield',
                                itemId: 'firstnameField',
                                name: 'fname',
                                fieldLabel: 'First Name',
                                allowBlank: false
                            },
                            {
                                xtype: 'textfield',
                                itemId: 'lastnameField',
                                name: 'lname',
                                fieldLabel: 'Last Name',
                                allowBlank: false
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
                                fieldLabel: 'Email',
                                vtype:'email'
                            },
                            {
                                xtype: 'textfield',
                                itemId: 'phoneField',
                                name: 'phone',
                                fieldLabel: 'Phone',
                                vtype:'phone'
                            },
                            {
                                xtype: 'checkbox',
                                itemId: 'adminField',
                                name: 'isAdminUser',
                                fieldLabel: 'Admin?'
                            }
                        ]
                    }, {
                        title: 'Groups',
                        itemId: 'groupsTab',
                        items: [{
                                xtype: 'form',
                                itemId: 'userGroupsForm',
                                border: 0,
                                bodyPadding: 5,
                                width: 600,
                                height: 350,
                                buttons: [
                                    {
                                        itemId: 'saveUserGroupsButton',
                                        text: 'Update',
                                        action: 'saveUserGroupsButton'
                                    },
                                    {
                                        itemId: 'cancelButton',
                                        text: 'Cancel',
                                        scope: this,
                                        handler: this.destroy
                                    }
                                ],
                                items: [
                                    {
                                        xtype: 'hidden',
                                        name: 'userId',
                                        value: this.selectedProjectId
                                    },
                                    {
                                        xtype: 'itemselector',
                                        name: 'groupIds',
                                        itemId: 'user-group-selector',
                                        anchor: '100%',
                                        fieldLabel: '',
                                        imagePath: '../../../extjs/ux/images/',
                                        store: this.groupsStore,
                                        displayField: 'name',
                                        valueField: 'id',
                                        allowBlank: false,
                                        msgTarget: 'side',
                                        fromTitle: 'Available',
                                        value: [],
                                        toTitle: 'Allocated'
                                    }
                                ]
                            }]
                    }]
            }

        ];
        this.on('afterrender', this.loadDefaultValues, this);
        this.callParent();
    },
    loadDefaultValues: function() {
        var saveBtn = this.down("form").getValues().id;
        if (globalvar.isAdminUser && saveBtn.text !== "0") {
            this.enableTabsForProject();
            this.loadUserGroups();
        } else {
            this.disableTabsForProject();
        }
    },
    enableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        this.down('#groupsTab').setDisabled(false);
    },
    disableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        this.down('#groupsTab').setDisabled(true);
    },
    loadUserGroups: function() {
        //calls the server and gets the selected groups for the selected project
        var url = './v1/usergroups/user/' + globalvar.currentUserId;
        Ext.Ajax.request({
            method: 'GET',
            url: url,
            headers: {
                'Accept': 'application/json'
            },
            scope: this,
            success: function(response, options) {
                var item = this.down("#user-group-selector");
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    item.setValue(responseData.results.groupIds);
                }
                else {
                    item.setValue([]);
                }
            }});
    }
});