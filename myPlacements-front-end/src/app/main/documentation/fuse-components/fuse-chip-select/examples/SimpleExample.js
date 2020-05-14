import React, { useState } from "react";
import { Typography } from "@material-ui/core";
import { FuseChipSelect } from "@fuse";

const suggestions = ["Sea", "Sky", "Forest", "Aerial", "Art"].map((item) => ({
  value: item,
  label: item,
}));

function SimpleExample() {
  const [tags, setTags] = useState([
    {
      value: "nature",
      label: "Nature",
    },
    {
      value: "city",
      label: "City",
    },
    {
      value: "landscape",
      label: "Landscape",
    },
  ]);

  function handleChipChange(add) {
    // const input = add;
    // const [arrq, setArrq] = useState("");
    // setArrq(add);
    // var str = new String(add);
    // var input = str.split(",");
    // if (input.length > 0) {
    //   //const [input] = add.split(",");
    //   input.map((item) => {
    //     setTags(item);
    //   });
    // } else setTags(add);
    setTags(add);
  }

  return (
    <div className="w-full max-w-sm pt-64 pb-224">
      <Typography className="text-24 mt-24 mb-8" component="h2">
        Standart
      </Typography>

      <FuseChipSelect
        className="w-full my-16"
        value={tags}
        onChange={(value) => {
          // String(value).split(",").length > 0
          //   ? String(value)
          //       .split(",")
          //       .map((item) => handleChipChange(item))
          //   :
          handleChipChange(value);
        }}
        placeholder="Select multiple tags"
        textFieldProps={{
          label: "Tags",
          InputLabelProps: {
            shrink: true,
          },
          variant: "standard",
        }}
        options={suggestions}
        isMulti
      />

      <Typography className="text-24 mt-24 mb-8" component="h2">
        Outlined
      </Typography>

      <FuseChipSelect
        className="w-full my-16"
        value={tags}
        onChange={handleChipChange}
        placeholder="Select multiple tags"
        textFieldProps={{
          label: "Tags",
          InputLabelProps: {
            shrink: true,
          },
          variant: "outlined",
        }}
        options={suggestions}
        isMulti
      />

      <Typography className="text-24 mt-24 mb-8" component="h2">
        Filled
      </Typography>

      <FuseChipSelect
        className="w-full my-16"
        value={tags}
        onChange={handleChipChange}
        placeholder="Select multiple tags"
        textFieldProps={{
          label: "Tags",
          InputLabelProps: {
            shrink: true,
          },
          variant: "filled",
        }}
        options={suggestions}
        isMulti
      />
    </div>
  );
}

export default SimpleExample;
