var InitiateSimpleDataTable = function () {
    return {
        init: function () {
            //Datatable Initiating
            var oTable = $('#simpledatatable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "iDisplayLength": 5,
                "oTableTools": {
                    "aButtons": [
                        "copy", "csv", "xls", "pdf", "print"
                    ]
                },
                "language": {
                    "search": "",
                    "sLengthMenu": "_MENU_",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },

                "aaSorting": []
            });


        }

    };

}();
var InitiateEditableDataTable = function () {
    return {
        init: function () {
            //Datatable Initiating
            // var oTable = $('#editabledatatable_1').dataTable({
            //     // "aLengthMenu": [
            //     //     [5, 15, 20, 100, -1],
            //     //     [5, 15, 20, 100, "All"]
            //     // ],
            //     // "iDisplayLength": 5,
            //     // "sPaginationType": "bootstrap",
            //     // "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
            //     // "oTableTools": {
            //     //     "aButtons": [
				    //     // "copy",
				    //     // "print",
				    //     // {
				    //     //     "sExtends": "collection",
				    //     //     "sButtonText": "Save <i class=\"fa fa-angle-down\"></i>",
				    //     //     "aButtons": ["csv", "xls", "pdf"]
				    //     // }]
            //     // },
            //     // "language": {
            //     //     "search": "",
            //     //     "sLengthMenu": "_MENU_",
            //     //     "oPaginate": {
            //     //         "sPrevious": "Prev",
            //     //         "sNext": "Next"
            //     //     }
            //     // },
            //     "aoColumns": [
            //       null,
            //       null,
            //       null,
            //       null,
            //       { "bSortable": false }
            //     ]
            // });

            // var isEditing = null;

            //Add New Row
            $('#editabledatatable_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '', '', '',
                        '<a href="#" class="btn btn-success btn-xs save"><i class="fa fa-edit"></i> Save</a> <a href="#" class="btn btn-warning btn-xs cancel"><i class="fa fa-times"></i> Cancel</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                isEditing = nRow;
            });

            //Delete an Existing Row
            $('#editabledatatable_1').on("click", 'a.delete', function (e) {
                e.preventDefault();

                if (confirm("Are You Sure To Delete This Row?") == false) {
                    return;
                }

                var nRow = $(this).parents('tr')[0];
                oTable.fnDeleteRow(nRow);
                alert("Row Has Been Deleted!");
            });

            //Cancel Editing or Adding a Row
            $('#editabledatatable_1').on("click", 'a.cancel', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    restoreRow(oTable, isEditing);
                    isEditing = null;
                }
            });

            //Edit A Row
            $('#editabledatatable_1').on("click", 'a.edit', function (e) {
                e.preventDefault();
                console.log("edit");
                var nRow = $(this).parents('tr')[0];

                // if (isEditing !== null && isEditing != nRow) {
                //     restoreRow(oTable, isEditing);
                //     editRow(oTable, nRow);
                //     isEditing = nRow;
                // } else {
                //     editRow(oTable, nRow);
                //     isEditing = nRow;
                // }
            });

            //Save an Editing Row
            $('#editabledatatable').on("click", 'a.save', function (e) {
                e.preventDefault();
                if (this.innerHTML.indexOf("Save") >= 0) {
                    saveRow(oTable, isEditing);
                    isEditing = null;
                    //Some Code to Highlight Updated Row
                }
            });


            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[3] + '">';
                jqTds[4].innerHTML = '<a href="#" class="btn btn-success btn-xs save"><i class="fa fa-save"></i> Save</a> <a href="#" class="btn btn-warning btn-xs cancel"><i class="fa fa-times"></i> Cancel</a>';
            }

            function saveRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a href="#" class="btn btn-info btn-xs edit"><i class="fa fa-edit"></i> Edit</a> <a href="#" class="btn btn-danger btn-xs delete"><i class="fa fa-trash-o"></i> Delete</a>', nRow, 4, false);
                oTable.fnDraw();
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a href="#" class="btn btn-info btn-xs edit"><i class="fa fa-edit"></i> Edit</a> <a href="#" class="btn btn-danger btn-xs delete"><i class="fa fa-trash-o"></i> Delete</a>', nRow, 4, false);
                oTable.fnDraw();
            }
        }

    };
}();
var InitiateGCCDataTable = function () {
    return {
        init: function () {
            /* Formatting function for row details */
            function fnFormatDetails(oTable, nTr) {
                var aData = oTable.fnGetData(nTr);
                var sOut = '<table>';
                sOut += '<tr><td rowspan="5" style="padding:0 10px 0 0;"><div style="width:150px;height:150px; "><img src="' + aData[4] + '"/ style="max-width:100%;"></div></td><td>类别ID:</td><td>' + aData[1] + '</td></tr>';
                sOut += '<tr><td>类别名:</td><td>' + aData[2] + '</td></tr>';
                sOut += '<tr><td>创建时间:</td><td>' + aData[3] + '</td></tr>';
                // sOut += '<tr><td>Positon:</td><td>' + aData[4] + '</td></tr>';
                // sOut += '<tr><td>Others:</td><td><a href="">Personal WebSite</a></td></tr>';
                sOut += '</table>';
                return sOut;
            }

            /*
             * Insert a 'details' column to the table
             */
            var nCloneTh = document.createElement('th');
            var nCloneTd = document.createElement('td');
            nCloneTd.innerHTML = '<i class="fa fa-plus-square-o row-details ficon">&#xe81a;</i>';

            $('#expandabledatatable thead tr').each(function () {
                this.insertBefore(nCloneTh, this.childNodes[0]);
            });

            $('#expandabledatatable tbody tr').each(function () {
                this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
            });

            /*
             * Initialize DataTables, with no sorting on the 'details' column
             */
            var oTable = $('#expandabledatatable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0] },
                    { "bVisible": false, "aTargets": [4] }
                ],
                "aaSorting": [[1, 'asc']],
                "aLengthMenu": [
                   [5, 15, 20, -1],
                   [5, 15, 20, "All"]
                ],
                "iDisplayLength": 10,
                "language": {
                    "search": "",
                    "sLengthMenu": "每页显示_MENU_条数据",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                }
            });


            $('#expandabledatatable').on('click', ' tbody td .row-details', function () {
                var nTr = $(this).parents('tr')[0];
                if (oTable.fnIsOpen(nTr)) {
                    /* This row is already open - close it */
                    $(this).html("&#xe81a;");
                    oTable.fnClose(nTr);
                }
                else {
                    /* Open this row */
                    $(this).html("&#xe819;");;
                    oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
                }
            });

            $('#expandabledatatable_column_toggler input[type="checkbox"]').change(function () {
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('body').on('click', '.dropdown-menu.hold-on-click', function (e) {
                e.stopPropagation();
            })

        }
    };
}();
var InitiateGCLDataTable = function () {
    return {
        init: function () {
            /* Formatting function for row details */
            function fnFormatDetails(oTable, nTr) {
                var aData = oTable.fnGetData(nTr);
                var sOut = '<table>';
                sOut += '<tr><td rowspan="5" style="padding:0 10px 0 0;"><div style="width:150px;height:150px; "><img src="' + aData[8] + '"/ style="max-width:100%;"></div></td><td>类别ID:</td><td>' + aData[1] + '</td><td>   父类别ID:</td><td>' + aData[5] + '</td></tr>';
                sOut += '<tr><td>俗名:</td><td>' + aData[2] + '</td><td>  </td><td>计价单位:</td><td>' + aData[6] + '</td><td>  </td><td>规格2:</td><td>'+ aData[10] +'</td><td>  </td><td>规格5:</td><td>'+ aData[13] +'</td></tr>';
                sOut += '<tr><td>学名:</td><td>' + aData[3] + '</td><td>  </td><td>创建时间:</td><td>' + aData[7] + '</td><td>  </td><td>规格3:</td><td>'+ aData[11] +'</td></tr>';
                sOut += '<tr><td>英文名:</td><td>' + aData[4] + '</td><td>  </td><td>规格1:</td><td>'+ aData[9] +'</td><td>  </td><td>规格4:</td><td>'+ aData[12] +'</td></tr>';
                sOut += '</table>';
                return sOut;
            }

            /*
             * Insert a 'details' column to the table
             */
            var nCloneTh = document.createElement('th');
            var nCloneTd = document.createElement('td');
            nCloneTd.innerHTML = '<i class="fa fa-plus-square-o row-details ficon">&#xe81a;</i>';

            $('#expandabledatatable thead tr').each(function () {
                this.insertBefore(nCloneTh, this.childNodes[0]);
            });

            $('#expandabledatatable tbody tr').each(function () {
                this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
            });

            /*
             * Initialize DataTables, with no sorting on the 'details' column
             */
            var oTable = $('#expandabledatatable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0] },
                    { "bVisible": false, "aTargets": [8] },
                    { "bVisible": false, "aTargets": [9] },
                    { "bVisible": false, "aTargets": [10] },
                    { "bVisible": false, "aTargets": [11] },
                    { "bVisible": false, "aTargets": [12] },
                    { "bVisible": false, "aTargets": [13] }
                ],
                "aaSorting": [[1, 'asc']],
                "aLengthMenu": [
                   [5, 15, 20, -1],
                   [5, 15, 20, "All"]
                ],
                "iDisplayLength": 6,
                "language": {
                    "search": "",
                    "sLengthMenu": "每页显示_MENU_条数据",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                }
            });


            $('#expandabledatatable').on('click', ' tbody td .row-details', function () {
                var nTr = $(this).parents('tr')[0];
                if (oTable.fnIsOpen(nTr)) {
                    /* This row is already open - close it */
                    $(this).html("&#xe81a;");
                    oTable.fnClose(nTr);
                }
                else {
                    /* Open this row */
                    $(this).html("&#xe819;");;
                    oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
                }
            });

            $('#expandabledatatable_column_toggler input[type="checkbox"]').change(function () {
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('body').on('click', '.dropdown-menu.hold-on-click', function (e) {
                e.stopPropagation();
            })

        }
    };
}();
var InitiateSearchableDataTable = function () {
    return {
        init: function () {
            var oTable = $('#searchable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "aaSorting": [[1, 'asc']],
                "aLengthMenu": [
                   [5, 15, 20, -1],
                   [5, 15, 20, "All"]
                ],
                "iDisplayLength": 10,
                "oTableTools": {
                    "aButtons": [
				        "copy",
				        "print",
				        {
				            "sExtends": "collection",
				            "sButtonText": "Save <i class=\"fa fa-angle-down\"></i>",
				            "aButtons": ["csv", "xls", "pdf"]
				        }]
                },
                "language": {
                    "search": "",
                    "sLengthMenu": "_MENU_",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                }
            });

            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                oTable.fnFilter(this.value, $("tfoot input").index(this));
            });

        }
    }
}();
