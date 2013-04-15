describe("chartDataAdapter", function() {

    it("should convert data", function() {
        var results =
            [{district : "district1", count : 12},
             {district : "district2", count : 13}]

        var data = extractData(results, "district", ["count"]);

        expect(data.xAxis).toEqual(["district1", "district2"]);
        expect(data.series.count).toEqual([12, 13]);
    });

    it("should convert data for multi series charts", function() {
        var results =
            [{district : "district1", series1 : 12, series2 : 10},
             {district : "district2", series1 : 13, series2 : 5}]

        var data = extractData(results, "district", ["series1", "series2"]);

        expect(data.xAxis).toEqual(["district1", "district2"]);
        expect(data.series.series1).toEqual([12, 13]);
        expect(data.series.series2).toEqual([10, 5]);
    });

})