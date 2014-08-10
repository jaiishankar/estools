Ext.define('estools.view.project.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.projectedit',
    // Default proeprties
    layout: 'fit',
    width: '680', height: '450',
    autoShow: false,
    closable: false,
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
                        title: 'Metrics',
                        itemId: 'metricsTab',
                        items: [{
                                xtype: 'projectmetricsgrid',
                                itemId:'projectmetricsGrid',
                                selectedProjectId: this.selectedProjectId
                            }],
                        buttons: [{
                                        itemId: 'saveMetricsButton',
                                        text: 'Upadte',
                                        action: 'saveMetrics'
                                    },
                                    {
                                        itemId: 'cancelButton',
                                        text: 'Cancel',
                                        scope: this,
                                        handler: this.destroy
                                    }]
                    }, {
                        title: 'Groups',
                        itemId: 'groupsTab',
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
        this.on('afterrender', this.getProjectGroupValues, this);
        // Add the save/cancel buttons
        this.callParent();
    },
    getProjectGroupValues: function() {
        if (this.selectedProjectId > 0) {
            this.enableTabsForProject();
            this.loadProjectGroups();
            this.down('#projectmetricsGrid').selectedProjectId = this.selectedProjectId;
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