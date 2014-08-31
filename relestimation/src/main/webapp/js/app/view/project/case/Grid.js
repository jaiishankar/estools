Ext.define('estools.view.project.case.Grid', {
    extend: 'Ext.grid.Panel',
    requires: ['estools.store.FeatureCases'],
    alias: 'widget.featurescasegrid',
    border: 0,
    id: 'featurecasesgridid',
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
    configs: {
        selectedFeatureId: 0
    },
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.columns = [
            {text: "ID", dataIndex: 'id', width: 5, sortable: true, hidden: true},
            {text: "Feature", dataIndex: 'featureId', width: 5, sortable: true, hidden: true},
            {text: "Name", dataIndex: 'name', sortable: true, hidden: false},
            {text: "Details", flex:1,dataIndex: 'taskDetails', sortable: true, hidden: false},
            {text: "Note", dataIndex: 'note', sortable: true, hidden: false},
            {text: "Questions", dataIndex: 'question', sortable: true, hidden: false},
            {text: "Priority", dataIndex: 'priorityId', sortable: false, hidden: false},
            {text: "Scoped", dataIndex: 'scoped', sortable: true, hidden: false},
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("FeatureCases");
        this.on('render', this.loadStore, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        this.getStore().getProxy().url = ('./v1/cases/feature/' + this.selectedFeatureId);
        this.getStore().load();
    }
});