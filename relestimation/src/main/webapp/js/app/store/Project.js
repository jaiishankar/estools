Ext.define('estools.store.Project', {
    extend: 'Ext.data.Store',
    model: 'estools.model.Project',
    storeId: 'projectStore',
    autoLoad: false,
    sorters: [
        {
            property: 'id',
            direction: 'ASC'
        }
    ]
});