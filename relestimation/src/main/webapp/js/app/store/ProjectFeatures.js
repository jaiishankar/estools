Ext.define('estools.store.ProjectFeatures', {
    extend: 'Ext.data.Store',
    model: 'estools.model.ProjectFeatures',
    storeId: 'projectFeaturessStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});