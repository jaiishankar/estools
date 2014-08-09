Ext.define('estools.store.AssumptionType', {
    extend: 'Ext.data.Store',
    model: 'estools.model.AssumptionType',
    storeId: 'assumTypeStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});