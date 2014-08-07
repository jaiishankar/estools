Ext.define('estools.store.Sizing', {
    extend: 'Ext.data.Store',
    model: 'estools.model.Sizing',
    storeId: 'sizingStore',
    autoLoad: true,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});