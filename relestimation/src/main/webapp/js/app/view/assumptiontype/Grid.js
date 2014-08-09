Ext.define('estools.view.assumptiontype.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.DevGroup'],
    alias: 'widget.assumtypegrid',
    border: 0,
    id: 'assumtypegridid',
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", width: 40, dataIndex: 'id', sortable: true, hidden: true},
            {text: "Assumption Type ", width: 300, dataIndex: 'typeName', sortable: true},
            {text: "created", width: 3, dataIndex: 'created', hidden: true},
            {text: "updated", width: 3, dataIndex: 'updated', hidden: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("AssumptionType");

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