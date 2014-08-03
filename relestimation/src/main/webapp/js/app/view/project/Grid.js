Ext.define('estools.view.project.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.Project'],
    alias: 'widget.projectgrid',
    border: 0,
    id:'projectgridid',
    // override
    initComponent : function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", width: 40, dataIndex: 'id', sortable: true, hidden:true},
            {text: "Project Name", width: 300, dataIndex: 'title', sortable: true},
            {text: "Project Description", width: 500, dataIndex: 'description', sortable: true},
            {text: "Owner ID", width: 40, dataIndex: 'ownerId', sortable: true, hidden:true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = new estools.store.Project({
            storeId: 'gridProjectProcessStore'
        });

        this.on('render', this.loadStore, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        this.getStore().getProxy().url = ('./v1/projects/both/'+ globalvar.currentUserId);
        this.getStore().load();
    }
});