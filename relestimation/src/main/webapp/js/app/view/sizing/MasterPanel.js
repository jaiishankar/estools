Ext.define('estools.view.sizing.MasterPanel', {
    extend: 'Ext.Panel',
    alias: 'widget.sizingmasterpanel',
    // Default properties
    layout: 'border',
    bodyPadding: 0,
    border: true,
    closable: false,
    padding: 0,
    title: 'Sizing',
    /**
     * Initialize Component.
     */
    initComponent: function() {

        // Set this panel's title
        this.title = 'Sizing';

        // Set the items (regions)
        this.items = [this.createGrid()];

        this.dockedItems = [this.createTopToolbar()];

        // call the superclass's initComponent implementation
        this.callParent();
    },
    // Create the 'Center' region that hold the grid
    createGrid: function() {
        this.grid = Ext.create('estools.view.sizing.Grid', {
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
    createTopToolbar: function() {
        this.toolbar = Ext.create('widget.toolbar', {
            items: [
                {text: 'New', iconCls: 'icon-add', action: 'newsizing'},
                {text: 'Delete', iconCls: 'icon-delete', action: 'deletesizing'},
                {text: 'Edit', iconCls: 'icon-edit', action: 'editsizing'}
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
    }

});
