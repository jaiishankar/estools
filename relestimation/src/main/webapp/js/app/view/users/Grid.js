Ext.define('estools.view.users.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.User'],
    alias: 'widget.usersgrid',
    id:'usersgridid',
    border: 0,
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", width: 40, dataIndex: 'id', sortable: true, hidden: true},
            {text: "User Name", width: 100, dataIndex: 'uname', sortable: true},
            {text: "First Name", width: 100, dataIndex: 'fname', sortable: true},
            {text: "Middle Name", width: 100, dataIndex: 'mname', sortable: true},
            {text: "Last Name", width: 100, dataIndex: 'lname', sortable: true},
            {text: "Email", width: 200, dataIndex: 'email', sortable: true},
            {text: "Phone", width: 100, dataIndex: 'phone', sortable: true},
            {text: "created", width: 3, dataIndex: 'created', hidden: true},
            {text: "updated", width: 3, dataIndex: 'updated', hidden: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("User");
        this.on('render', this.loadStore, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        this.getStore().load();
    }
});