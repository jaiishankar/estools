Ext.define('estools.store.ProjectMetrics', {
    extend: 'Ext.data.Store',
    model: 'estools.model.ProjectMetrics',
    storeId: 'projectMetricsStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});
