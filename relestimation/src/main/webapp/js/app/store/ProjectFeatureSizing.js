Ext.define('estools.store.ProjectFeatureSizing', {
    extend: 'Ext.data.Store',
    model: 'estools.model.ProjectFeatureSizing',
    storeId: 'projectFeatureSizingStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});