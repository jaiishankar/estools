Ext.define('estools.store.User', {
    extend: 'Ext.data.Store',
    model: 'estools.model.User',
    storeId: 'userStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});