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

    it("should not include rows with zero counts", function() {
        var results =
            [{district : "district1", count : 11},
                {district : "district2", count : 0}]

        var data = extractData(results, "district", ["count"]);

        expect(data.xAxis).toEqual(["district1"]);
        expect(data.series.count).toEqual([11]);
    });

    it("should include rows with zero counts", function() {
        var results =
            [{district : "district1", count : 11},
                {district : "district2", count : 0}]

        var data = extractData(results, "district", ["count"], false);

        expect(data.xAxis).toEqual(["district1", "district2"]);
        expect(data.series.count).toEqual([11, 0]);
    });

    it("should not include rows with zero counts for multi series charts", function() {
        var results =
            [{district : "district1", series1 : 0, series2 : 10},
             {district : "district2", series1 : 13, series2 : 5},
             {district : "district3", series1 : 0, series2 : 0}]

        var data = extractData(results, "district", ["series1", "series2"]);

        expect(data.xAxis).toEqual(["district1", "district2"]);
        expect(data.series.series1).toEqual([0, 13]);
        expect(data.series.series2).toEqual([10, 5]);
    });

    it("should include rows with zero counts for multi series charts", function() {
        var results =
            [{district : "district1", series1 : 0, series2 : 10},
                {district : "district2", series1 : 13, series2 : 5},
                {district : "district3", series1 : 0, series2 : 0}]

        var data = extractData(results, "district", ["series1", "series2"], false);

        expect(data.xAxis).toEqual(["district1", "district2", "district3"]);
        expect(data.series.series1).toEqual([0, 13, 0]);
        expect(data.series.series2).toEqual([10, 5, 0]);

    });
})