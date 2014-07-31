Ext.define('estools.view.project.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.projectedit',
    // Default proeprties
    layout: 'fit',
    autoShow: false,
    config: {
        selectedProjectId: 0
    },
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Add/Edit Project Window';
        this.groupsStore = Ext.StoreMgr.get("gridDevProcessStore");
        if (this.groupsStore.getCount() <= 0) {
            this.groupsStore.load();
        } else {
            this.groupsStore.sync();
        }
        // Create and add the form
        this.items = [
            {
                xtype: 'tabpanel',
                width: '400', height: '350',
                items: [
                    {
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
                                        xtype: 'hiddenfield',
                                        name: 'id',
                                        value: 0
                                    },
                                    {
                                        xtype: 'textfield',
                                        itemId: 'projecttitleField',
                                        name: 'title',
                                        fieldLabel: 'Project Title'
                                    },
                                    {
                                        xtype: 'textarea',
                                        itemId: 'projectdescField',
                                        name: 'description',
                                        fieldLabel: 'Project description'
                                    },
                                    {
                                    }
                                ]
                            }]
                    }, {
                        title: 'Groups',
                        items: [{
                                xtype: 'form',
                                itemId: 'projectGroupsForm',
                                border: 0,
                                bodyPadding: 5,
                                width: 600,
                                height: 350,
                                buttons: [
                                    {
                                        itemId: 'saveProjectGroupsButton',
                                        text: 'Update',
                                        action: 'saveProjectGroupsButton'
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
                                        name: 'projectId',
                                        value: this.selectedProjectId
                                    },
                                    {
                                        xtype: 'itemselector',
                                        name: 'groupIds',
                                        id: 'itemselector-field',
                                        anchor: '100%',
                                        fieldLabel: 'Select Groups',
                                        imagePath: '../../../extjs/ux/images/',
                                        store: this.groupsStore,
                                        displayField: 'name',
                                        valueField: 'id',
                                        value: this.getProjectGroupValues(),
                                        allowBlank: false,
                                        msgTarget: 'side',
                                        fromTitle: 'Available',
                                        toTitle: 'Selected'
                                    }
                                ]
                            }]
                    }
                ]
            }
        ];
        // Add the save/cancel buttons
        this.callParent();
    },
    getProjectGroupValues : function(){
        //calls the server and gets the selected groups for the selected project
        return [7,6];
    }
});