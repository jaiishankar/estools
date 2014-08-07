Ext.define('estools.view.priority.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.Priority'],
    alias: 'widget.prioritygrid',
    border: 0,
    id: 'prioritygridid',
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", width: 10, dataIndex: 'id', sortable: true, hidden: true},
            {text: "Priority Name", width: 200, dataIndex: 'priority', sortable: true},
            {text: "created", width: 3, dataIndex: 'created', hidden: true},
            {text: "updated", width: 3, dataIndex: 'updated', hidden: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("Priority");

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