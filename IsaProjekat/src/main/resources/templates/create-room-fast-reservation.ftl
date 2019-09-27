    <h2>Create Fast Reservation For Room</h2>
    <form>
        <div class="row">
            <div class="date">
                <div class="depart">
                    <h3>Set check-in date</h3>
                    <input id="datepickerfast" ng-model="crfCtrl.checkInDate" class="form-control" name="Text" type="text"
                    value="yyyy-MM-dd" onfocus="this.value = '';"
                    onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
                </div>
                <div class="return">
                    <h3> Set check-out date</h3>
                    <input id="datepickerfast1" ng-model="crfCtrl.checkOutDate" class="form-control" name="Text" type="text"
                    value="yyyy-MM-dd" onfocus="this.value = '';"
                    onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-sm-3">
                <label for="discountfast">Discount(%):</label>
                <input type="number" class="form-control" ng-model="crfCtrl.discount" id="discountfast" />
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <button ng-click="crfCtrl.saveFastReservation()" class="btn btn-primary">Save</button>
            </div>
        </div>
    <form>

<script>
    $(function () {
        $("#datepickerfast,#datepickerfast1").datepicker();
    });
</script>