Ext.define('estools.view.devgroup.MasterPanel', {
    extend: 'Ext.Panel',
    alias: 'widget.devgroupmasterpanel',
    // Default properties
    layout: 'border',
    bodyPadding: 0,
    border: true,
    closable: false,
    padding: 0,
    title: 'Groups',
    /**
     * Initialize Component.
     */
    initComponent: function() {

        // Set this panel's title
        this.title = 'Groups';

        // Create the detail panel to be displayed in the preview pane
        this.previewpane = Ext.create('estools.view.devgroup.DetailPanel');

        // Set the items (regions)
        this.items = [this.createGrid(), this.createSouth(), this.createEast()];

        this.dockedItems = [this.createTopToolbar()];

        // call the superclass's initComponent implementation
        this.callParent();
    },
    // Create the 'Center' region that hold the grid
    createGrid: function() {
        this.grid = Ext.create('estools.view.devgroup.Grid', {
            region: 'center',
            flex: 1,
            listeners: {
                viewready: function(thisGrid) {
                    thisGrid.getSelectionModel().select(0);
                }
            }
        });

        return this.grid;
    },
    // Create the 'South' region that hold (by default) the Detail Pane
    // The Dev Group could move the preview pane to the right or completly hide it
    createSouth: function() {
        this.south = Ext.create('Ext.panel.Panel', {
            layout: 'fit',
            region: 'south',
            split: true,
            collapsible: true,
            border: false,
            preventHeader: true,
            title: 'Preview Pane',
            flex: 1,
            hidden: true
        });

        return this.south;
    },
    // Create 'East' region that could eventually hold the Detail Pane
    // If the dev grp select the preview pane to be on the right side
    createEast: function() {
        this.east = Ext.create('Ext.panel.Panel', {
            layout: 'fit',
            region: 'east',
            split: true,
            collapsible: true,
            border: false,
            preventHeader: true,
            title: 'Preview Panel',
            flex: 1,
            items: this.previewpane

        });
        return this.east;
    },
    createTopToolbar: function() {
        this.toolbar = Ext.create('widget.toolbar', {
            items: [{
                    xtype: 'cycle',
                    text: 'Reading Pane',
                    showText: true,
                    prependText: 'Preview: ',
                    scope: this,
                    menu: {
                        id: 'devgrp-reading-menu',
                        items: [{
                                text: 'Bottom',
                                checked: true,
                                iconCls: 'preview-bottom'
                            }, {
                                text: 'Right',
                                iconCls: 'preview-right'
                            }, {
                                text: 'Hide',
                                iconCls: 'preview-hide'
                            }]
                    },
                    changeHandler: this.onPreviewPaneChange
                },
                {text: 'New', iconCls: 'icon-group-add', action: 'newgroup'},
                {text: 'Delete', iconCls: 'icon-group-delete', action: 'deletegroup'},
                {text: 'Edit', iconCls: 'icon-group-edit', action: 'editgroup'}
            ]
        });

        return this.toolbar;
    },
    // Handles the PreviewPane position change 
    onPreviewPaneChange: function(cycleBtn, activeItem) {
        switch (activeItem.text) {
            case 'Bottom':
                this.east.hide();
                this.south.show();
                this.south.add(this.previewpane);
                break;
            case 'Right':
                this.south.hide();
                this.east.show();
                this.east.add(this.previewpane);
                break;
            default:
                this.south.hide();
                this.east.hide();
                break;
        }
    },
    // override initEvents
    initEvents: function() {
        // call the superclass's initEvents implementation
        this.callParent();

        // now add application specific events
        // notice we use the selectionmodel's rowselect event rather
        // than a click event from the grid to provide key navigation
        // as well as mouse navigation
        var devgrpsGridSM = this.grid.getSelectionModel();
        devgrpsGridSM.on('selectionchange', this.onRowSelect, this);

    },
    // add a method called onRowSelect
    // This matches the method signature as defined by the 'rowselect'
    // event defined in Ext.selection.RowModel
    onRowSelect: function(sm, rs) {
        // getComponent will retrieve itemId's or id's. Note that itemId's
        // are scoped locally to this instance of a component to avoid
        // conflicts with the ComponentManager
        if (rs.length) {
            var detailPanel = this.previewpane;
            detailPanel.updateDetail(rs[0].data);
        }

    },
    onSummaryToggle: function(sm, rs) {
        console.log(rs.data);
        //this.editPanel.show();

    }

});
