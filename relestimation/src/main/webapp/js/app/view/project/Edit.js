Ext.define('estools.view.project.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.projectedit',
    layout: 'fit',
    width: '680', height: '450',
    maximized: true,
    autoShow: false,
    closable: true,
    config: {
        selectedProjectId: 0
    },
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Add/Edit Project Window';
        this.groupsStore = Ext.StoreMgr.get("DevGroup");
        if (this.groupsStore.getCount() <= 0) {
            this.groupsStore.load();
        } else {
            this.groupsStore.sync();
        }


        // Create and add the form
        this.items = [
            {
                xtype: 'tabpanel',
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
                                        xtype: 'hiddenfield',
                                        name: 'ownerId',
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
                                    }
                                ]
                            }]
                    }, {
                        title: 'Metrics',
                        itemId: 'metricsTab',
                        layout: 'border',
                        items: [{
                                region: 'center',
                                xtype: 'projectmetricsgrid',
                                itemId: 'projectmetricslistgrid',
                                selectedProjectId: this.selectedProjectId
                            }, {
                                region: 'south',
                                xtype: 'form',
                                id: 'projectmetricsForm',
                                border: 0,
                                boddyPadding: 5,
                                selectedProjectId:0,
                                buttons: [
                                    {
                                        itemId: 'newMetricsButton',
                                        text: 'New',
                                        action: 'newMetrics'
                                    },
                                    {
                                        itemId: 'saveMetricsButton',
                                        text: 'Save',
                                        action: 'saveMetrics'
                                    },
                                    {
                                        itemId: 'deleteMetricsButton',
                                        text: 'Delete',
                                        action: 'deleteMetrics'
                                    },
                                    {
                                        itemId: 'cancelButton',
                                        text: 'Cancel',
                                        scope: this,
                                        handler: this.destroy
                                    }],
                                items: [
                                    {
                                        xtype: 'hidden',
                                        name: 'projectId',
                                        value: this.selectedProjectId
                                    },
                                    {
                                        xtype: 'hidden',
                                        name: 'id'
                                    },
                                    {
                                        fieldLabel: 'Enter Project Metrics:',
                                        width: 800,
                                        xtype: 'textarea',
                                        name: 'description',
                                        allowBlank:false
                                    }
                                ]
                            }]

                    }, {
                        title: 'Groups',
                        itemId: 'groupsTab',
                        items: [{
                                xtype: 'form',
                                id: 'projectGroupsForm',
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
                                        itemId: 'project-itemselector-field',
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
                                        toTitle: 'Selected'

                                    }
                                ]
                            }]
                    }
                ]
            }
        ];
        this.on('afterrender', this.loadDefaultValues, this);
        // Add the save/cancel buttons
        this.callParent();
    },
    loadDefaultValues: function() {
        if (this.selectedProjectId > 0) {
            this.enableTabsForProject();
            this.loadProjectGroups();
            this.down('#projectmetricslistgrid').selectedProjectId = this.selectedProjectId;
            this.down('#projectmetricsForm').selectedProjectId = this.selectedProjectId;
            
        } else {
            this.disableTabsForProject();
        }
    },
    enableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        this.down('#groupsTab').setDisabled(false);
        this.down('#metricsTab').setDisabled(false);
    },
    disableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        this.down('#groupsTab').setDisabled(true);
        this.down('#metricsTab').setDisabled(true);
    },
    loadProjectGroups: function() {
        //calls the server and gets the selected groups for the selected project
        var url = './v1/projectgroups/project/' + this.selectedProjectId;
        console.log(url);
        Ext.Ajax.request({
            method: 'GET',
            url: url,
            headers: {
                'Accept': 'application/json'
            },
            scope: this,
            success: function(response, options) {
                var item = this.down("#project-itemselector-field");
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