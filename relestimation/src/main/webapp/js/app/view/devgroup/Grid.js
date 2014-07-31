Ext.define('estools.view.devgroup.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.DevGroup'],
    alias: 'widget.devgroupsgrid',
    border: 0,
    id: 'devgroupgridid',
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", width: 40, dataIndex: 'id', sortable: true, hidden: true},
            {text: "Group Name", width: 300, dataIndex: 'name', sortable: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = new estools.store.DevGroup({
            storeId: 'gridDevProcessStore'
        });

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