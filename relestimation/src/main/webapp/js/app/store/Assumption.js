Ext.define('estools.store.Assumption', {
    extend: 'Ext.data.Store',
    model: 'estools.model.Assumption',
    storeId: 'assumptionStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});


