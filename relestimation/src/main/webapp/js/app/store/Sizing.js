Ext.define('estools.store.Sizing', {
    extend: 'Ext.data.Store',
    model: 'estools.model.Sizing',
    storeId: 'sizingStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});