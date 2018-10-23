function prob = CDF_nowcast(count)
%#codegen
    
    
    infile = 'data.txt';
    data = coder.load(infile, '-ascii');
    f = data(:, 1);
    x = data(:, 2);
    
    prob = interp1(x, f, count, 'nearest');
    
end












