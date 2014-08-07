Ext.define('estools.view.sizing.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.Sizing'],
    alias: 'widget.sizinggrid',
    border: 0,
    id: 'sizinggridid',
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", width: 10, dataIndex: 'id', sortable: true, hidden: true},
            {text: "Sizing Name", width: 200, dataIndex: 'sizeName', sortable: true},
            {text: "Sizing Value", width: 200, dataIndex: 'sizeValue', sortable: true},
            {text: "Unit of Measurment", width: 200, dataIndex: 'sizeName', sortable: true},
            {text: "created", width: 3, dataIndex: 'created', hidden: true},
            {text: "updated", width: 3, dataIndex: 'updated', hidden: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("Sizing");

        this.on('render', this.loadStore, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        if (this.getStore().getCount() <= 0) {
            this.getStore().load();
        } else {
            this.getStore().sync();
        }
    }
});