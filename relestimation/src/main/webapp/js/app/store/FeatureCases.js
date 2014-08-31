Ext.define('estools.store.FeatureCases', {
    extend: 'Ext.data.Store',
    model: 'estools.model.FeatureCases',
    storeId: 'featureCasesStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});