Ext.define('estools.store.Priority', {
    extend: 'Ext.data.Store',
    model: 'estools.model.Priority',
    storeId: 'priorityStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});