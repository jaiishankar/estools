Ext.define('estools.view.project.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.projectedit',
    layout: 'fit',
    id: 'projectmaineditpanel',
    width: '680', height: '450',
    maximized: true,
    autoShow: false,
    closable: true,
    config: {
        selectedProjectId: 0,
        selectedFeatureId: 0
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
                itemId: 'projectmaintabpanel',
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
                                        fieldLabel: 'Project Title',
                                        allowBlank: false
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
                                selectedProjectId: 0,
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
                                        allowBlank: false
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
                    }, {
                        title: 'Features',
                        itemId: 'featureTab',
                        layout: 'border',
                        items: [{
                                region: 'center',
                                xtype: 'projectfeaturesgrid',
                                itemId: 'projectfeatureslistgrid',
                                selectedProjectId: this.selectedProjectId
                            }, {
                                region: 'south',
                                xtype: 'panel',
                                layout: {
                                    type: 'hbox',
                                    align: 'stretch'
                                },
                                items: [{
                                        flex: 2,
                                        title:'Details',
                                        xtype: 'form',
                                        id: 'projectfeaturesForm',
                                        border: 0,
                                        boddyPadding: 5,
                                        selectedProjectId: 0,
                                        buttons: [
                                            {
                                                itemId: 'newFeaturesButton',
                                                text: 'New',
                                                action: 'newFeatures'
                                            },
                                            {
                                                itemId: 'saveFeaturesButton',
                                                text: 'Save',
                                                action: 'saveFeatures'
                                            },
                                            {
                                                itemId: 'deleteFeaturesButton',
                                                text: 'Delete',
                                                action: 'deleteFeatures'
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
                                                fieldLabel: 'Title',
                                                name: 'title',
                                                xtype: 'textfield'
                                            },
                                            {
                                                fieldLabel: 'Name',
                                                name: 'name',
                                                xtype: 'textfield',
                                                allowBlank: false
                                            },
                                            {
                                                fieldLabel: 'Enter Project Task:',
                                                width: 800,
                                                xtype: 'textarea',
                                                name: 'task',
                                                allowBlank: false
                                            },
                                            {
                                                fieldLabel: 'Scoped (Y/N)',
                                                name: 'scoped',
                                                xtype: 'textfield',
                                                size: 1,
                                                msgTarget: 'side',
                                                validator: function(value) {
                                                    if (value && ((value === 'y' || value === 'Y') || (value === 'N' || value === 'n'))) {
                                                        return true;
                                                    } else {
                                                        return 'Input can be only Y or N';
                                                    }
                                                }
                                            },
                                            {
                                                xtype: 'combo',
                                                fieldLabel: 'Type',
                                                editable: false,
                                                itemId: 'featuresComboTypeData',
                                                name: 'type',
                                                valueField: 'id',
                                                displayField: 'name',
                                                store: {
                                                    fields: ['id', 'name'],
                                                    data: [
                                                        {id: 1, name: 'MVP'},
                                                        {id: 2, name: 'Non MVP'}
                                                    ]
                                                }
                                            }
                                        ]
                                    },{
                                        flex:2,
                                        title:'Sizings',
                                        disable: true,
                                        xtype: 'featuressizinggrid',
                                        itemId:'featuressizinggriditemid',
                                        selectedProjectId: this.selectedProjectId
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
            this.down('#projectfeatureslistgrid').selectedProjectId = this.selectedProjectId;
            this.down('#projectfeaturesForm').selectedProjectId = this.selectedProjectId;
            this.down('#featuressizinggriditemid').selectedProjectId = this.selectedProjectId;

        } else {
            this.disableTabsForProject();
        }
    },
    enableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        this.down('#groupsTab').setDisabled(false);
        this.down('#metricsTab').setDisabled(false);
        this.down('#featureTab').setDisabled(false);
    },
    disableTabsForProject: function() {
        //disable the groups tab abd other tabs.
        this.down('#groupsTab').setDisabled(true);
        this.down('#metricsTab').setDisabled(true);
        this.down('#featureTab').setDisabled(true);
    },
    loadProjectGroups: function() {
        //calls the server and gets the selected groups for the selected project
        var url = './v1/projectgroups/project/' + this.selectedProjectId;
        Ext.Ajax.request({
            method: 'GET',
            url: url,
            headers: {
                'Accept': 'application/json'
            },
            scope: this,
            success: function(response) {
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