Ext.define('estools.view.project.metrics.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.ProjectMetrics'],
    alias: 'widget.projectmetricsgrid',
    border: 0,
    id: 'projectmetricsgridid',
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
            {text: "ID", dataIndex: 'id', width: 50, sortable: true, hidden: false},
            {text: "Project Metrics", flex: 1, dataIndex: 'description', sortable: false, tdCls: 'wrap'},
            {text: "Project Owner", dataIndex: 'projectId', sortable: false, hidden: true},
            {text: "Owner ID", dataIndex: 'ownerId', sortable: true, hidden: true}
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("ProjectMetrics");

        this.on('render', this.loadStore, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        this.getStore().getProxy().url = ('./v1/metrics/project/' + this.selectedProjectId);
        this.getStore().load();
    }
});