Ext.define('estools.view.devgroup.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.devgroupedit',
    // Default proeprties
    layout: 'fit',
    autoShow: false,
    width: '680', height: '450',
    maximized: true,
    selectedGroupdId: 0,
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Add/Edit Group Window';
        this.store = Ext.StoreMgr.get("User");
        if (this.store.getCount() <= 0) {
            this.store.load();
        } else {
            this.store.sync();
        }
        // Create and add the form
        this.items = [{
                xtype: 'tabpanel',
                items: [{
                        title: 'Main',
                        items: [{
                                xtype: 'form',
                                itemId: 'editForm',
                                border: 0,
                                bodyPadding: 5,
                                buttons: [
                                    {
                                        itemId: 'saveButton',
                                        text: 'Upadte',
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
                                        itemId: 'groupnameField',
                                        name: 'name',
                                        width: 550,
                                        fieldLabel: 'Group Name',
                                        allowBlank: false
                                    }
                                ]
                            }
                        ]
                    }, {
                        title: 'Users',
                        itemId: 'usergroupsTab',
                        items: [{
                                xtype: 'form',
                                id: 'userstogroupsForm',
                                border: 0,
                                bodyPadding: 5,
                                width: 600,
                                height: 350,
                                buttons: [
                                    {
                                        itemId: 'saveusertogroupsButton',
                                        text: 'Update',
                                        action: 'saveusertogroupsButton'
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
                                        name: 'groupId',
                                        value: this.selectedGroupId
                                    },
                                    {
                                        xtype: 'itemselector',
                                        name: 'userIds',
                                        itemId: 'user-grop-itemselector-field',
                                        anchor: '100%',
                                        fieldLabel: '',
                                        imagePath: '../../../extjs/ux/images/',
                                        store: this.store,
                                        displayField: 'lname',
                                        valueField: 'id',
                                        allowBlank: false,
                                        msgTarget: 'side',
                                        fromTitle: 'Available',
                                        value: [],
                                        toTitle: 'Selected'

                                    }
                                ]
                            }]
                    }
                ]
            }];

        // Add the save/cancel buttons
        this.buttons = [
            {
                itemId: 'cancelButton',
                text: 'Cancel',
                scope: this,
                handler: this.destroy
            }
        ];

        this.on('afterrender', this.loadDefaultValues, this);
        this.callParent();
    },
    loadDefaultValues: function() {
        if (this.selectedGroupdId > 0) {
            this.enableTabsForProject();
            this.loadProjectGroups();
            //this.down('#projectmetricslistgrid').selectedProjectId = this.selectedProjectId;
            //this.down('#projectmetricsForm').selectedProjectId = this.selectedProjectId;

        } else {
            this.disableTabsForProject();
        }
    },
    enableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        //this.down('#groupsTab').setDisabled(false);
        //this.down('#metricsTab').setDisabled(false);
    },
    disableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        //this.down('#groupsTab').setDisabled(true);
        //this.down('#metricsTab').setDisabled(true);
    },
    loadProjectGroups: function() {
        //calls the server and gets the selected users for the selected group
        var url = './v1/usergroups/group/' + this.selectedGroupdId;
        Ext.Ajax.request({
            method: 'GET',
            url: url,
            headers: {
                'Accept': 'application/json'
            },
            scope: this,
            success: function(response, options) {
                var item = this.down("#user-grop-itemselector-field");
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    item.setValue(responseData.results.userIds);
                }
                else {
                    item.setValue([]);
                }
            }});
    }
});


