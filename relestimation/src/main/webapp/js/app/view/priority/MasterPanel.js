Ext.define('estools.view.priority.MasterPanel', {
    extend: 'Ext.Panel',
    alias: 'widget.prioritymasterpanel',
    // Default properties
    layout: 'border',
    bodyPadding: 0,
    border: true,
    closable: false,
    padding: 0,
    title: 'Priority',
    /**
     * Initialize Component.
     */
    initComponent: function() {

        // Set this panel's title
        this.title = 'Priority';

        // Create the detail panel to be displayed in the preview pane
        this.previewpane = Ext.create('estools.view.priority.DetailPanel');
        //this.editPanel = Ext.create('estools.view.sizing.Edit');

        // Set the items (regions)
        this.items = [this.createGrid(), this.createSouth(), this.createEast()];

        this.dockedItems = [this.createTopToolbar()];

        // call the superclass's initComponent implementation
        this.callParent();
    },
    // Create the 'Center' region that hold the grid
    createGrid: function() {
        this.grid = Ext.create('estools.view.priority.Grid', {
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
    // The user could move the preview pane to the right or completly hide it
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
    // If the user select the preview pane to be on the right side
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
                        id: 'priority-reading-menu',
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
                {text: 'New', iconCls: 'icon-add', action: 'newpriority'},
                {text: 'Delete', iconCls: 'icon-delete', action: 'deletepriority'},
                {text: 'Edit', iconCls: 'icon-edit', action: 'editpriority'}
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
        var usersGridSM = this.grid.getSelectionModel();
        usersGridSM.on('selectionchange', this.onRowSelect, this);

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

    }

});
