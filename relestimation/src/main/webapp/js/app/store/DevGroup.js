Ext.define('estools.store.DevGroup', {
    extend: 'Ext.data.Store',
    model: 'estools.model.DevGroup',
    storeId: 'devGroupStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});