Ext.define('estools.view.project.features.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.ProjectFeatures'],
    alias: 'widget.projectfeaturesgrid',
    border: 0,
    id: 'projectfeaturesgridid',
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
    configs: {
        selectedProjectId: 0
    },
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", dataIndex: 'id', width: 50, sortable: true, hidden: true},
            {text: "Grey Bar", dataIndex: 'title', sortable: true, hidden: false},
            {text: "Task", flex: 1, dataIndex: 'task', sortable: false, tdCls: 'wrap'},
            {text: "Scoped", dataIndex: 'scoped', sortable: true, hidden: false},
             {text: "Name", dataIndex: 'name', sortable: true, hidden: false},
            {text: "type", dataIndex: 'type', sortable: false, hidden: true},
            {text: "Project Owner", dataIndex: 'projectId', sortable: false, hidden: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("ProjectFeatures");

        this.on('render', this.loadStore, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        this.getStore().getProxy().url = ('./v1/features/project/' + this.selectedProjectId);
        this.getStore().load();
    }
});